// schedule_generator.js

const timeSlots = [
  ["0900", "0950"],
  ["1000", "1050"],
  ["1100", "1150"],
  ["1200", "1250"],
  ["1300", "1340"],
  ["1340", "1430"],
  ["1440", "1530"],
  ["1540", "1630"],
  ["1640", "1730"],
];

const endTimeOptions = [
  "0950",
  "1050",
  "1150",
  "1250",
  "1430",
  "1530",
  "1630",
  "1730",
];

function timeToNumber(t) {
  return parseInt(t);
}

function isWeekend(date) {
  const day = date.getDay();
  return day === 0 || day === 6;
}

function generateSchedule(
  startDate,
  startTime,
  endDate,
  endTime,
  instructorCode,
  subjectCode
) {
  const holidayRaw = localStorage.getItem("holidaySchedule");
  const holidayRanges = holidayRaw
    ? JSON.parse(holidayRaw).map((h) => ({
        date: h.date.replaceAll("-", ""),
        startTime: h.startTime,
        endTime: h.endTime,
      }))
    : [];
  const result = [];
  const start = new Date(startDate);
  const end = new Date(endDate);

  for (let d = new Date(start); d <= end; d.setDate(d.getDate() + 1)) {
    if (isWeekend(d)) continue;

    const dateStr = d.toISOString().slice(0, 10).replace(/-/g, "");

    for (const [slotStart, slotEnd] of timeSlots) {
      // 휴일 범위와 겹치는지 확인
      const isHoliday = holidayRanges.some(
        (h) =>
          h.date === dateStr &&
          !(slotEnd <= h.startTime || slotStart >= h.endTime)
      );
      if (isHoliday) continue;
      if (
        dateStr === startDate.replaceAll("-", "") &&
        timeToNumber(slotEnd) <= timeToNumber(startTime)
      )
        continue;
      if (
        dateStr === endDate.replaceAll("-", "") &&
        timeToNumber(slotStart) >= timeToNumber(endTime)
      )
        continue;

      const isLunch = slotStart === "1300";

      result.push({
        date: dateStr,
        startTime: slotStart,
        endTime: slotEnd,
        isRemoteOrVacation: "",
        timeSlot: isLunch ? 2 : 1,
        instructorCode: isLunch ? "" : instructorCode,
        roomCode: "",
        subjectCode: isLunch ? "" : subjectCode,
      });
    }
  }
  return result;
}

function addModule() {
  const container = document.getElementById("module-container");
  const div = document.createElement("div");
  div.className = "module";
  div.innerHTML = `
      <div>
        <label>시작일자: <input type="date" class="startDate"></label>
        <label>시작시간:
          <select class="startTime">
            ${timeSlots
              .map(([start]) => `<option value="${start}">${start}</option>`)
              .join("")}
          </select>
        </label>
        <label>종료일자: <input type="date" class="endDate"></label>
        <label>종료시간:
          <select class="endTime">
            ${endTimeOptions
              .map(
                (end) =>
                  `<option value="${end}"${
                    end === "1730" ? " selected" : ""
                  }>${end}</option>`
              )
              .join("")}
          </select>
        </label>
        <label>강사코드: <input type="text" class="instructorCode"></label>
        <label>교과목코드: <input type="text" class="subjectCode"></label>
        <button type="button" class="remove-module">삭제</button>
      </div>`;
  container.appendChild(div);

  div.querySelector(".remove-module").addEventListener("click", () => {
    container.removeChild(div);
  });
}

function detectOverlap(schedules) {
  const sorted = schedules
    .slice()
    .sort(
      (a, b) =>
        a.date.localeCompare(b.date) || a.startTime.localeCompare(b.startTime)
    );
  for (let i = 0; i < sorted.length - 1; i++) {
    const a = sorted[i];
    const b = sorted[i + 1];
    if (a.date === b.date && a.endTime > b.startTime) {
      return true;
    }
  }
  return false;
}

function detectGaps(schedules) {
  const slotsByDate = {};
  for (const item of schedules) {
    if (!slotsByDate[item.date]) slotsByDate[item.date] = [];
    slotsByDate[item.date].push(item);
  }

  for (const date in slotsByDate) {
    const slots = slotsByDate[date].sort((a, b) =>
      a.startTime.localeCompare(b.startTime)
    );
    for (let i = 0; i < slots.length - 1; i++) {
      const currentEnd = slots[i].endTime;
      const nextStart = slots[i + 1].startTime;
      const validPair = timeSlots.some(
        ([s, e], idx) =>
          e === currentEnd &&
          timeSlots[idx + 1] &&
          timeSlots[idx + 1][0] === nextStart
      );
      if (currentEnd === "1250" && nextStart === "1340") continue;
      if (!validPair) return true;
    }
  }
  return false;
}

function detectMissingLectures(schedules) {
  const slotsByDate = {};
  const holidayRaw = localStorage.getItem("holidaySchedule");
  const holidayRanges = holidayRaw
    ? JSON.parse(holidayRaw).map((h) => ({
        date: h.date.replaceAll("-", ""),
        startTime: h.startTime,
        endTime: h.endTime,
      }))
    : [];

  for (const item of schedules) {
    if (!slotsByDate[item.date]) slotsByDate[item.date] = [];
    slotsByDate[item.date].push(item);
  }

  for (const date in slotsByDate) {
    const slots = slotsByDate[date];
    const lectureSlots = slots.filter((s) => s.timeSlot === 1);

    // 해당 날짜에 유효한(휴일 제외) 전체 강의 슬롯 개수 계산
    const validSlots = timeSlots.filter(([start, end]) => {
      return !holidayRanges.some(
        (h) => h.date === date && !(end <= h.startTime || start >= h.endTime)
      );
    });

    const expectedCount = validSlots.filter(
      ([start]) => start !== "1300"
    ).length;

    if (lectureSlots.length !== expectedCount) return true;
    for (const s of lectureSlots) {
      if (!s.instructorCode || !s.subjectCode) return true;
    }
  }
  return false;
}

document.getElementById("add-module").addEventListener("click", addModule);

document.getElementById("generate").addEventListener("click", () => {
  const container = document.getElementById("module-container");
  const modules = Array.from(document.querySelectorAll(".module"));
  let allSchedules = [];

  const enrichedModules = modules.map((mod) => {
    const startDate = mod.querySelector(".startDate").value;
    const startTime = mod.querySelector(".startTime").value;
    const endDate = mod.querySelector(".endDate").value;
    const endTime = mod.querySelector(".endTime").value;
    const instructorCode = mod.querySelector(".instructorCode").value;
    const subjectCode = mod.querySelector(".subjectCode").value;
    return {
      mod,
      startDate,
      startTime,
      endDate,
      endTime,
      instructorCode,
      subjectCode,
    };
  });

  enrichedModules.sort(
    (a, b) =>
      a.startDate.localeCompare(b.startDate) ||
      a.startTime.localeCompare(b.startTime)
  );
  container.innerHTML = "";

  enrichedModules.forEach((data) => {
    container.appendChild(data.mod);
    const schedule = generateSchedule(
      data.startDate,
      data.startTime,
      data.endDate,
      data.endTime,
      data.instructorCode,
      data.subjectCode
    );
    allSchedules = allSchedules.concat(schedule);
  });

  allSchedules.sort(
    (a, b) =>
      a.date.localeCompare(b.date) || a.startTime.localeCompare(b.startTime)
  );

  if (detectOverlap(allSchedules)) {
    alert("시간이 겹치는 일정이 존재합니다.");
    return;
  }
  if (detectGaps(allSchedules)) {
    alert("일정 사이에 빈 시간이 존재합니다.");
    return;
  }
  if (detectMissingLectures(allSchedules)) {
    alert(
      "강의일에는 오전 4시간, 오후 4시간이 모두 존재하고 강사/교과목이 지정되어야 합니다."
    );
    return;
  }

  document.getElementById("output").textContent = JSON.stringify(
    allSchedules,
    null,
    2
  );
});

addModule();

// 엑셀로 저장 버튼 추가
const downloadButton = document.createElement("button");
downloadButton.textContent = "엑셀 파일 생성";
downloadButton.id = "downloadExcel";
document.getElementById("generate").after(downloadButton);

// 과정명 및 강의실코드 입력창 추가
const titleInput = document.createElement("input");
titleInput.type = "text";
titleInput.placeholder = "과정명";
titleInput.id = "courseTitle";
titleInput.style.marginRight = "10px";

const roomCodeInput = document.createElement("input");
roomCodeInput.type = "text";
roomCodeInput.placeholder = "강의실 코드";
roomCodeInput.id = "roomCode";
roomCodeInput.style.marginRight = "10px";

document.querySelector("h2").after(roomCodeInput);
document.querySelector("h2").after(titleInput);

// 엑셀 파일 저장 기능 구현
function downloadExcel(schedule) {
  const headers = [
    "훈련일자",
    "훈련시작시간",
    "훈련종료시간",
    "방학/원격여부",
    "시작시간",
    "시간구분",
    "훈련강사코드",
    "교육장소(강의실)코드",
    "교과목(및 능력단위)코드",
  ];

  const roomCodeValue = document.getElementById("roomCode").value || "";
  const rows = schedule.map((s) => [
    s.date,
    s.startTime,
    s.endTime,
    s.isRemoteOrVacation,
    s.startTime,
    s.timeSlot,
    s.instructorCode,
    s.timeSlot === 2 ? "" : roomCodeValue,
    s.subjectCode,
  ]);

  const worksheet = XLSX.utils.aoa_to_sheet([headers, ...rows]);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "일정");

  const courseTitle =
    document.getElementById("courseTitle").value || "훈련일정";
  const now = new Date();
  const timeString = now.toTimeString().split(" ")[0].replace(/:/g, "");
  const fileName = `${courseTitle}_${timeString}.xlsx`;
  XLSX.writeFile(workbook, fileName);
}

// 엑셀 저장 버튼 이벤트
document.getElementById("downloadExcel").addEventListener("click", () => {
  const data = document.getElementById("output").textContent;
  if (!data) {
    alert("먼저 전체 일정 생성을 클릭해주세요.");
    return;
  }
  const schedule = JSON.parse(data);
  downloadExcel(schedule);
});

// JSON 저장 버튼 생성 및 이벤트
const jsonButton = document.createElement("button");
jsonButton.textContent = "JSON 파일 저장";
jsonButton.id = "downloadJson";
document.getElementById("downloadExcel").after(jsonButton);

jsonButton.addEventListener("click", () => {
  const data = document.getElementById("output").textContent;
  if (!data) {
    alert("먼저 전체 일정 생성을 클릭해주세요.");
    return;
  }
  const schedule = JSON.parse(data);
  const courseTitle =
    document.getElementById("courseTitle").value || "훈련일정";
  const now = new Date();
  const timeString = now.toTimeString().split(" ")[0].replace(/:/g, "");
  const fileName = `${courseTitle}_${timeString}.json`;

  const blob = new Blob([JSON.stringify(schedule, null, 2)], {
    type: "application/json",
  });
  const link = document.createElement("a");
  link.href = URL.createObjectURL(blob);
  link.download = fileName;
  link.click();
  URL.revokeObjectURL(link.href);
});

const jsonLoadButton = document.createElement("button");
jsonLoadButton.textContent = "JSON 파일 로드";
jsonLoadButton.id = "uploadJson";
document.getElementById("downloadJson").after(jsonLoadButton);

jsonLoadButton.addEventListener("click", () => {
  const input = document.createElement("input");
  input.type = "file";
  input.accept = ".json";
  input.onchange = async (event) => {
    const file = event.target.files[0];
    if (!file) return;
    const text = await file.text();
    const schedule = JSON.parse(text);
    if (!Array.isArray(schedule)) {
      alert("잘못된 JSON 파일입니다.");
      return;
    }

    const container = document.getElementById("module-container");
    container.innerHTML = "";

    schedule.sort(
      (a, b) =>
        a.date.localeCompare(b.date) || a.startTime.localeCompare(b.startTime)
    );

    let currentModule = null;
    let currentCode = {};

    let grouped = [];
    let group = [];
    let prevInstructor = null;
    let prevSubject = null;

    schedule.forEach((item) => {
      if (item.timeSlot !== 1) return;
      const codeChanged =
        item.instructorCode !== prevInstructor ||
        item.subjectCode !== prevSubject;
      if (group.length && codeChanged) {
        grouped.push(group);
        group = [];
      }
      group.push(item);
      prevInstructor = item.instructorCode;
      prevSubject = item.subjectCode;
    });
    if (group.length) grouped.push(group);

    grouped.forEach((group) => {
      const start = group[0];
      const end = group[group.length - 1];

      const module = document.createElement("div");
      module.className = "module";
      module.innerHTML = `
    <div>
      <label>시작일자: <input type="date" class="startDate" value="${start.date.slice(
        0,
        4
      )}-${start.date.slice(4, 6)}-${start.date.slice(6, 8)}"></label>
      <label>시작시간: <select class="startTime">${timeSlots
        .map(
          ([t]) =>
            `<option value="${t}" ${
              t === start.startTime ? "selected" : ""
            }>${t}</option>`
        )
        .join("")}</select></label>
      <label>종료일자: <input type="date" class="endDate" value="${end.date.slice(
        0,
        4
      )}-${end.date.slice(4, 6)}-${end.date.slice(6, 8)}"></label>
      <label>종료시간: <select class="endTime">${endTimeOptions
        .map(
          (t) =>
            `<option value="${t}" ${
              t === end.endTime ? "selected" : ""
            }>${t}</option>`
        )
        .join("")}</select></label>
      <label>강사코드: <input type="text" class="instructorCode" value="${
        end.instructorCode
      }"></label>
      <label>교과목코드: <input type="text" class="subjectCode" value="${
        end.subjectCode
      }"></label>
      <button type="button" class="remove-module">삭제</button>
    </div>
  `;
      module
        .querySelector(".remove-module")
        .addEventListener("click", () => module.remove());
      container.appendChild(module);
    });
  };
  input.click();
});
