<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- オリジナルCSS -->
<link rel="stylesheet" type="text/css"
      href="<c:url value='/css/style.css' />">
<title>HOME</title>
</head>
<body>
	<header class="page-header">
		<h2>Focus Step</h2>
		<nav>
			<ul class="page-nav">
				<li><a href="Test.jsp">テスト</a></li>
				<li><a href="">今月の予定</a></li>
				<li><a href="">今までの振り返り</a></li>
				<li><a href="Logout">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<div class="wrapper center">
		<div class="top">
			<div class="item">
				<img class="img-inf" src="images/information.png">
			</div>
			<div class="item-speech">
				<div class="speechBubble">
						<h2><c:out value="${fullName}" />
						さん、おはようございます🌞</h2>
						<p>
						今日も一日、気持ちよくスタートしていきましょう<br>
						あなたの力がチームにとって大きな支えです！
					</p>
				</div>
			</div>
		</div>
		<div class="main">
			<section class="item-menu">
				<ul class="menu">
					<li><a href="PostTask">タスク追加</a></li>
					<li><a href="EditTask">タスク編集</a></li>
					<li><a href="">今日の振り返り</a></li>
					<li><a href="">明日の準備</a></li>
				</ul>
			</section>
			<section class="item-flex">
				<div class="item-top outline">
					<h2>今日のタスク</h2>
					<c:if test="${not empty todayTasks}">
							 <table class="table-task">
						        <thead>
						            <tr>
						            	<th>優先順位</th>
						                <th>タスク</th>
						                <th>状態</th>
						                <th>目標時間</th>
						                <th>実質時間</th>
						            </tr>
						        </thead>
						        <tbody>
						            <c:forEach var="t" items="${todayTasks}">
						                <tr>
						                	<td>${t.priority}</td>
						                    <td>${t.title}</td>
						                    <td>${t.status}</td>
						                    <td>${t.target}分</td>
						                    <td>${t.actual}分</td>
						                </tr>
						            </c:forEach>
						        </tbody>
						    </table>
					</c:if>
					<c:if test="${empty todayTasks}">
						<p>表示するタスクがありません。</p>
					</c:if>
				</div>
				<div class="item-bottom outline">
					<h2>今やること</h2>
					<c:if test="${not empty nextTask}">
				        <h4>${nextTask.title}</h4>
				        <p>ステータス: ${nextTask.status}</p>
				    </c:if>
				</div>
			</section>
			<section class="item-timer outline">
			  <h2>タイマー</h2>
			  <c:if test="${not empty nextTask}">
			    <input type="hidden" id="taskId" value="${nextTask.taskId}" />
			    <div class="timer-ui">
			      <div class="time-display" id="timerDisplay"></div>
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
    console.log("timerDisplay:", timerDisplay);//確認用
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
    	console.log("updateTimer called");//確認用
        const minutes = Math.floor(totalSeconds / 60);
        const seconds = totalSeconds % 60;
        const mm = String(minutes).padStart(2, "0");
        const ss = String(seconds).padStart(2, "0");
        timerDisplay.innerHTML = `${mm}:${ss}`;
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