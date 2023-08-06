
function actualizarPedido(id_pedido) {
    // Obtener los valores del formulario
    let estado = document.getElementById('estado_' + id_pedido).value;
  
    
    //obtengo la hora
    const fechaActual = new Date();
    const horaFormateada = fechaActual.toLocaleTimeString();
   
    let formData = new FormData();
    formData.append('accion', 'Actualizar');
    formData.append('id_pedido', id_pedido);
    formData.append('estado', estado);
    formData.append('hora', horaFormateada);
    
   // Enviar los datos al controlador JSP usando una solicitud AJAX con FormData
    fetch('/Hamburguesas/ControladorPedido', {
      method: 'POST',
      body: formData
      
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
    
    })
    .then(data => {
      // Aquí puedes manejar la respuesta del servidor si es necesario
      // Recargar la página después de procesar la respuesta
      window.location.reload();
      
    })
    .catch(error => {
      // Aquí puedes manejar errores en la solicitud o en la respuesta del servidor
      console.error('Fetch error:', error);
    });
}
