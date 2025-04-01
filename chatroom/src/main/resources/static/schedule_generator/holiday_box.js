// holiday_box.js

const HOLIDAY_STORAGE_KEY = "holidaySchedule";

function createHolidayModule(date = "", startTime = "0900", endTime = "1730") {
  const div = document.createElement("div");
  div.className = "holiday-module";
  div.style.marginBottom = "5px";
  div.innerHTML = `
    <label>날짜: <input type="date" class="holidayDate" value="${date}"></label>
    <label>시작시간:
      <select class="holidayStartTime">
        ${[
          "0900",
          "1000",
          "1100",
          "1200",
          "1300",
          "1340",
          "1440",
          "1540",
          "1640",
        ]
          .map(
            (time) =>
              `<option value="${time}" ${
                time === startTime ? "selected" : ""
              }>${time}</option>`
          )
          .join("")}
      </select>
    </label>
    <label>종료시간:
      <select class="holidayEndTime">
        ${[
          "0950",
          "1050",
          "1150",
          "1250",
          "1340",
          "1430",
          "1530",
          "1630",
          "1730",
        ]
          .map(
            (time) =>
              `<option value="${time}" ${
                time === endTime ? "selected" : ""
              }>${time}</option>`
          )
          .join("")}
      </select>
    </label>
    <button type="button" class="remove-holiday">삭제</button>
  `;

  div.querySelector(".remove-holiday").addEventListener("click", () => {
    div.remove();
    saveHolidayData();
  });

  return div;
}

function addHolidayModule(date = "", startTime = "0900", endTime = "1730") {
  const container = document.getElementById("holiday-module-container");
  const module = createHolidayModule(date, startTime, endTime);
  container.appendChild(module);
}

function saveHolidayData() {
  const modules = document.querySelectorAll(".holiday-module");
  const holidays = Array.from(modules).map((mod) => ({
    date: mod.querySelector(".holidayDate").value,
    startTime: mod.querySelector(".holidayStartTime").value,
    endTime: mod.querySelector(".holidayEndTime").value,
  }));
  localStorage.setItem(HOLIDAY_STORAGE_KEY, JSON.stringify(holidays));
}

function loadHolidayData() {
  const stored = localStorage.getItem(HOLIDAY_STORAGE_KEY);
  if (!stored) return;
  const holidays = JSON.parse(stored);
  holidays.forEach((h) => addHolidayModule(h.date, h.startTime, h.endTime));
}

document.addEventListener("DOMContentLoaded", () => {
  const holidayBox = document.createElement("div");
  holidayBox.style.border = "2px solid #999";
  holidayBox.style.padding = "10px";
  holidayBox.style.marginBottom = "20px";

  const title = document.createElement("h3");
  title.textContent = "휴일";
  holidayBox.appendChild(title);

  const moduleContainer = document.createElement("div");
  moduleContainer.id = "holiday-module-container";
  holidayBox.appendChild(moduleContainer);

  const addButton = document.createElement("button");
  addButton.textContent = "+ 휴일 모듈 추가";
  addButton.addEventListener("click", () => {
    addHolidayModule();
    saveHolidayData();
  });
  holidayBox.appendChild(addButton);

  const main = document.body;
  main.insertBefore(holidayBox, main.firstChild);

  loadHolidayData();
});

// 자동 저장 기능
window.addEventListener("change", (e) => {
  if (e.target.closest(".holiday-module")) {
    saveHolidayData();
  }
});
