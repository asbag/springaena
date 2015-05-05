<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actividad 1</title>
<!-- CSS de Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">

<!-- librerías opcionales que activan el soporte de HTML5 para IE8 -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<h1>Gestión de Aena</h1>
	<ul class="nav nav-pills">
		<li class="active"><a
			href="${pageContext.request.contextPath}/avion/listar_caducados">
				Listado Aviones Caducados</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/avion/listar_espanioles">Listado
				Aviones Españoles</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/aeropuerto/listar_aeropuertos">Listar
				Puertas Embarque</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/avion/num_asientos">N&uacute;mero
				de Asientos Reservados</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/avion/num_asientos_ocupados">N&uacute;mero
				de Asientos Ocupados</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/avion/insercion">Añadir
				Avi&oacute;n</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/pasajero/gestion">Gestión
				Pasajeros</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/aeropuerto/gestion">Gestión
				Aeropuertos</a></li>
	</ul>

	<!-- Librería jQuery requerida por los plugins de JavaScript -->
	<script src="http://code.jquery.com/jquery.js"></script>

	<!-- Todos los plugins JavaScript de Bootstrap (también puedes
         incluir archivos JavaScript individuales de los únicos
         plugins que utilices) -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>