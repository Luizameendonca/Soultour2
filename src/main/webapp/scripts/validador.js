/**
 * Validação de form
 */
 
 function validar() {
	let nome = formCadastro.nome.value
	let email = formCadastro.email.value
	let senha = formCadastro.senha.value
	
	if(nome === "") {
		alert("Digite o seu nome")
		formCadastro.nome.focus()
		return false
	} else if (email === "") {
				alert("Digite o seu email")
		formCadastro.email.focus()
		return false
	}else if (senha === "") {
				alert("Digite uma senha")
		formCadastro.senha.focus()
		return false
	}else {
		document.forms["formCadastro"].submit()
	}
	
}