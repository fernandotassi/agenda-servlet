<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="estilo.css">
<link rel="icon" href="imagens/cachorro.png">
<script src="scripts/validador.js"></script>
<title>editar</title>
</head>
<body>
<img src="imagens/cachorro.png" width="80px" height="80px" ></img>
 <form name="fcontato" action="upar">
               <table>
                             <tr>
                                     <td><input type="text" name="id" placeholder="id" id="caixa2" readonly 
                                                              value="<% out.print(request.getAttribute("id")); %>"></td>
                             </tr>
                             <tr>
                                   <td><input type="text" name="nome" placeholder="nome" class="caixa" 
                                                             value="<% out.print(request.getAttribute("nome")); %>"></td>
                             </tr>                                          
                             <tr>
                                    <td><input type="text" name="fone" placeholder="fone" class="caixa1"
                                                               value="<% out.print(request.getAttribute("fone")); %>"></td>
                             </tr>
                             <tr>
                                   <td><input type="text" name="email" placeholder="email" class="caixa"
                                                             value="<% out.print(request.getAttribute("email")); %>"></td>
                             </tr>
               </table>
               <input type="button"  value="editar" class="botao" onclick="validar()">
 </form>
</body>
</html>