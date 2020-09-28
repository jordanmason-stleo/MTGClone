
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
</head>
<body>
    <h2 class="hello-title">Hello ${name}!</h2>
    <form action="/greet" method="post">
        <label for="fname">First name:</label>
        <input type="text" id="name" name="name"><br><br>
        <input type="submit" value="Submit">
      </form>
</body>
</html>