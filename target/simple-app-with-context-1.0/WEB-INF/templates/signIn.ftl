<html>
<head>
    <script type="application/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="application/javascript">
        function signIn(event) {
            event.preventDefault();

            console.log("aaaa");
            let email = $("#email");
            let password = $("#password");
            $.ajax({
                url: "/signIn",
                data: {
                    "email": email.val(),
                    "password": password.val()
                },
                type: "post",
                dataType: "json",
            })
        }
    </script>
</head>
<body>
<form action="/signIn" method="post" onsubmit="signIn()">
    <input name="email" type="text" placeholder="email" id="email">
    <input name="password" type="password" placeholder="password" id="password">
    <input type="submit">
</form>
</body>

</html>