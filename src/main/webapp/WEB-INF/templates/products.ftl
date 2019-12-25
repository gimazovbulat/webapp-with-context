<html>
<head>
</head>
<body>
<table>
    <tr>
        <th>name</th>
        <th>price</th>
    </tr>
    <#list products as product>
        <tr>
            <th>${product.name}</th>
            <th>${product.price}</th>
        </tr>

    </#list>
</table>
</body>
</html>