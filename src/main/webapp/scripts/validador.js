function validar()
{
	   let nome = fcontato.nome.value;
	   let fone = fcontato.fone.value;
	   let email = fcontato.email.value;
	   
	   if(nome === "")
		{
		        alert('campo nome obrigatório');
				fcontato.nome.focus();
				return false;
		}
	   else if(fone === "")
		{
		         alert('campo fone obrigatório');
				 fcontato.fone.focus();
				 return false;
		 }
	    else if(email === "")
		{
			      alert('campo email obrigatório');
				  fcontato.email.focus();
				  return false;
		 }
		 else
			   document.forms["fcontato"].submit();      
}