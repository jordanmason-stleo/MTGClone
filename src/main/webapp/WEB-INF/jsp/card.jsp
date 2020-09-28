<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
   <head>
    <style>
        .container {
  width: 350px;
  clear: both;
}

.container input {
  width: 100%;
  clear: both;


}
    </style>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
</head>
<body>
    <h2 class="hello-title">Hello ${name}!</h2>
    <div class="container">

    <form action="/card" method="post">
        <label for="cardname">Card Name:</label>
        <input type="text" id="cardname" name="cardname"><br>
        <label for="manacost">Mana Cost:</label>
        <input type="text" id="manacost" name="manacost"><br>
        <label for="power">Power:</label>
        <input type="text" id="power" name="power"><br>
        <label for="toughness">Toughness:</label>
        <input type="text" id="toughness" name="toughness"><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description"><br>
        <label for="creaturetype">Creature Type:</label>
        <input type="text" id="creaturetype" name="creaturetype"><br>
        <br> <input style="width:30%;display:block;margin:0 auto;" type="submit" value="Submit">
      </form>

      </div>
      <table style="width:100%">
        <tr>
          <th>Card Name</th>
          <th>Mana Cost</th>
          <th>Power</th>
          <th>Toughness</th>
          <th>Description</th>
          <th>Creature Type</th>
        </tr>
        <tr>
        <c:if test="${not empty allcards}">
            
               <c:forEach var="listValue" items="${allcards}">
                   <td>${listValue.cardName}</td>
                   <td>${listValue.manaCost}</td>
                    <td>${listValue.power}</td>
                    <td>${listValue.toughness}</td>
                    <td>${listValue.description}</td>
                    <td>${listValue.creatureType}</td>
               </c:forEach>
            
        </c:if>
        </tr>
      </table>
</body>
</html>