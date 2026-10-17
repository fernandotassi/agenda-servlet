function confirmar(id)
{
	     let res = confirm("confirmar a exclusão");
		 if(res === true)
		 { 
			  window.location.href = "deletar?id=" + id
			  //alert(id)
		   }	 	
}