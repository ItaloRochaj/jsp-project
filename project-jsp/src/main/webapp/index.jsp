<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Projeto em JSP</title>
    
    <style type="text/css">
    form {
        position: absolute;
        top: 40%;
        left: 33%;
        right: 33%;
    }
    
    h5 {
        position: absolute;
        top: 30%;
        left: 33%;
    }
    
    .msg {
        position: absolute;
        top: 70%;
        left: 33%;
    }
    </style>
</head>
<body>

<h5>Estudos em JSP</h5>

<form action="ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
    <input type="hidden" value="<%= request.getParameter("url") %>" name="url">

    <div class="col-md-6">
        <label class="form-label">Login</label>
        <input class="form-control" id="Login" name="login" type="text" required>
        <div class="invalid-feedback">
            Informe seu Login!
        </div>
    </div>

    <div class="col-md-6">
        <label class="form-label">Senha</label>
        <input class="form-control" id="senha" name="senha" type="password" required>
        <div class="invalid-feedback">
            Informe sua Senha!
        </div>
    </div>

    <div class="col-12">
        <input type="submit" value="Acessar" class="btn btn-primary">
    </div>
</form>

<h5 class="msg">${msg}</h5>

<script>
(function () {
  'use strict'

  var forms = document.querySelectorAll('.needs-validation')

  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()
</script>
</body>
</html>
