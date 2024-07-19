<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 성공</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    body {
        font-family: Arial, sans-serif;        
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh; 
        margin: 0;
    }
    #content {       
        border-radius: 8px;
        padding: 10px;
        max-width: 500px;
        width: 100%;        
    }
    h1 {
        color: #333;
    }
    p {
        color: #666;
        font-size: 1.5em; 
    }  
</style>
</head>
<body>
    <div id="content">
        <h1>회원가입을 축하합니다!</h1>
        <p>회원가입이 성공적으로 완료되었습니다.</p>
        <a href="#" class="btn btn-primary mt-3" onclick="redirectToMainPage()">메인 페이지로 이동</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        function redirectToMainPage() {
            //  메인 페이지로 이동
            // 예를 들어 window.location.href = '메인페이지 URL';            
        }
    </script>
</body>
</html>