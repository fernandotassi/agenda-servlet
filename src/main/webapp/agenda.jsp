<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelos.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
		ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");	
//        for (int i = 0; i < lista.size(); i++)
//		    {
//		    out.println(lista.get(i).getId());
//		    out.println(lista.get(i).getNome());
//		    out.println(lista.get(i).getFone());
//		    out.println(lista.get(i).getEmail());
//		     }
		
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="icon" href="imagens/cachorro.png">
<link rel="stylesheet" href="estilo.css">
<script src="scripts/confirmador.js"></script>
<title>contatos</title>
</head>
<body>
	<img src="imagens/cachorro.png" width="80px" height="80px"></img>
	<h3>registrar</h3>
	<a href="registro.html" class="botao" target="_blank">novo</a>
	<a href="relatorio" class="botao1">relatório pdf</a>
    <table id="tabela">
                 <thead>
                               <tr>
                                     <th>Id</th>
                                     <th>Nome</th>
                                     <th>Fone</th>
                                     <th>Email</th>
                                     <th>opções</th>
                               </tr>
                 </thead>
                 <tbody>
                                <% for(int i = 0; i < lista.size(); i++) {%>
                                        <tr>
                                               <td><%= lista.get(i).getId() %></td>
                                               <td><%= lista.get(i).getNome() %></td>
                                               <td><%= lista.get(i).getFone() %></td>
                                               <td><%= lista.get(i).getEmail() %></td>
                                               <td><a href="seletor?id=<%= lista.get(i).getId() %>" class="botao">editar</a>
                                                        <a href="javascript: confirmar(<%= lista.get(i).getId() %>)" class="botao1">excluir</a>
                                                </td>
                                        </tr>
                                <% } %> 
                 </tbody>
    </table>
    
</body>
</html>