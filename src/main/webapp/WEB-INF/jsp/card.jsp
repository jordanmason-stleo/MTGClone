<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
   <head>
     <link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
    <style>
        .container {
  background-color:#4F4F4F;
  border-radius:15px;
  width:1885px;
}

td, th{
    padding: 5px 5px 5px 5px;

        }
table, td, th{
    border: 3px solid #CFCACA;
    border-collapse: collapse;
    width: 1885px;
    font-size: 26px;
    color: #CFCACA;
    
}
.container label {
  position: relative;font-size:20px;
  }
  .container input, textarea{
    background-color:#CFCACA;position:relative;border-radius:5px;font-size:20px;
  }
    </style>
    <meta charset="UTF-8">
    <title>Hello ${name}!</title>
</head>
<body style="background-color:#0F0F0F;font-family: 'Ubuntu', sans-serif;">
  <li><a href="/login" style="color:#C10E0E">Login / Sign Up</a></li> </ul>
    <h2 style="color:#CFCACA;position:relative;left:820px;">Magic Card Creator</h2>
   
    <div class="container">
    
    <form action="/card" method="post">
        <br><br><label style="left:210px;font-size:26px;"for="cardname">Card Name:</label>
        <input style="left:210px;font-size:24px;width:300px;"placeholder="Card Title"type="text" id="cardname" name="cardname"><br><br>
        <label style="left:150px;"for="manacost">Mana Cost:</label>
        <input style="left:150px;"type="text" id="manacost" name="manacost">
        <label style="left:250px;"for="power">Power:</label>
        <input style="left:250px;"type="text" id="power" name="power">
        <label style="left:350px;"for="toughness">Toughness:</label>
        <input style="left:350px;"type="text" id="toughness"name="toughness">
        <label style="left:450px;"for="creaturetype">Spell Type:</label>
        <select style="background-color:#CFCACA;position:relative;border-radius:4px;left:450px;font-size:20px;width:250px;"id="creaturetype"name="creaturetype">
          <option value="Evocation">Evocation</option>
          <option value="Ward">Ward</option>
          <option value="Curse">Curse</option>
        </select>
        <br><br>
        <label style="vertical-align:middle;left:145px;"for="description">Description:</label>
        <textarea style="border-radius:5px;vertical-align:middle;left:145px;font-size:18px;width:658px;height:120px;"placeholder="Flavor text, etc."type="text" id="description" name="description"></textarea>
       
        <label style="left:245px;font-size:14px;"for="username">Username:</label>
        <input style="left:245px;font-size:14px;"type="text" id="username" name="username">
        <label style="left:300px;font-size:14px;"for="password">Password:</label>
        <input style="left:300px;font-size:14px;"type="password" id="password" name="password"><br>
        
        <br> <input style="height:40px;width:15%;display:block;margin:0 auto;" type="submit" value="Submit"><br><br>
      </form>
      </div>
      <br>
      
      
      <table style>
        <tr>
          <th>Card Name</th>
          <th>Mana Cost</th>
          <th>Power</th>
          <th>Toughness</th>
          <th>Spell Type</th>
          <th>Description</th>         
        </tr>
        
        <c:if test="${not empty allcards}">
            
               <c:forEach var="listValue" items="${allcards}">
                   <tr></tr>
                   <td>${listValue.cardName}</td>
                   <td>${listValue.manaCost}</td>
                    <td>${listValue.power}</td>
                    <td>${listValue.toughness}</td>
                    <td>${listValue.creatureType}</td>
                    <td>${listValue.description}</td>                    
                    </tr>
               </c:forEach>
            
        </c:if>
        
      </table>
    
</body>
</html>