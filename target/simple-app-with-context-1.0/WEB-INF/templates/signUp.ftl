<html>

<head>
    <meta http-equiv="Content-Type" content="text/json; charset=utf-8">
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="application/javascript">
        function signUp(event) {
            event.preventDefault();

            console.log("aaaa");
            let email = $("#email");
            let password = $("#password");
            let nickname = $("#nickname");
            $.ajax({
                url: "/signUn",
                data: {
                    "email": email.val(),
                    "password": password.val(),
                    "nickname": nickname.val()
                },
                type: "post",
                dataType: "json",
            })
        }
    </script>
</head>
<body>
<form action="/signUp" method="post" onsubmit="signUp()">
    <input name="email" type="text" placeholder="email" id="email">
    <input name="password" type="password" placeholder="password" id="password">
    <input name="nickname" type="text" placeholder="nickname" id="nickname">
    <input type="submit">
</form>
</body>

</html>