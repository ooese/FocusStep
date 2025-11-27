<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="item-timer outline">
			  <h2>タイマー</h2>
			  <c:if test="${not empty nextTask}">
			    <input type="hidden" id="taskId" value="${nextTask.taskId}" />
			    <div class="timer-ui">
			      <div class="time-display" id="timerDisplay">0分</div>
			      <button class="circle-button start" id="startBtn">スタート</button>
			      <button class="circle-button pause" id="pauseBtn">一時停止</button>
			      <button class="circle-button finish" id="finishBtn">完了</button>
			    </div>
			  </c:if>
			</section>
		</div>
	</div>

<script>
document.addEventListener("DOMContentLoaded", () => {
    const timerDisplay = document.getElementById("timerDisplay");
    const startBtn = document.getElementById("startBtn");
    const pauseBtn = document.getElementById("pauseBtn");
    const finishBtn = document.getElementById("finishBtn");
    const taskIdInput = document.getElementById("taskId");

    if (!timerDisplay || !startBtn || !pauseBtn || !finishBtn || !taskIdInput) return;

    let totalSeconds = 0;     // 経過時間（秒）
    let running = false;      // タイマーが動作中か
    let timerInterval = null; // setIntervalのID

    // タイマー表示を更新
    function updateTimer() {
        const minutes = Math.floor(totalSeconds / 60);
        const seconds = totalSeconds % 60;
        const mm = String(minutes).padStart(2, "0");
        const ss = String(seconds).padStart(2, "0");
        timerDisplay.innerText = `${mm}:${ss}`;
    }

    // 初期表示
    updateTimer();

    // スタート / 再開
    startBtn.addEventListener("click", () => {
        if (running) return;
        if (!taskIdInput.value) {
            alert("実行するタスクがありません。");
            return;
        }
        running = true;
        timerInterval = setInterval(() => {
            totalSeconds += 1;
            updateTimer();
        }, 1000);
    });

    // 一時停止
    pauseBtn.addEventListener("click", () => {
        if (!running) return;
        running = false;
        clearInterval(timerInterval);
    });

    // 完了
    finishBtn.addEventListener("click", () => {
        if (!taskIdInput.value) {
            alert("完了するタスクがありません。");
            return;
        }
        running = false;
        clearInterval(timerInterval);

        const totalMinutes = Math.ceil(totalSeconds / 60);

        fetch("FinishTask", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "taskId=" + taskIdInput.value + "&actualMinutes=" + totalMinutes
        })
        .then(res => res.text())
        .then(msg => {
            alert("記録が完了しました。 実績時間: " + totalMinutes + "分");
            location.reload();
        });
    });
});
</script>
</body>
</html>