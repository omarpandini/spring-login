$(document).ready(function () {
	
	console.log('Version 1.00');
	const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
	const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
	$('#table-listagem-usuarios').DataTable();

});