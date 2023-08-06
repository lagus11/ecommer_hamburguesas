function ocultarTarjeta() {
  const efectivo = document.getElementById('efectivo');
  const tarjeta = document.getElementById('input_tarjeta');
  const label_tarjeta = document.getElementById('label_tarjeta');

  if (efectivo.checked) {
    tarjeta.style.display = 'none'; // Ocultar el input cuando el checkbox está seleccionado
    label_tarjeta.style.display = 'none'; // Ocultar el input cuando el checkbox está seleccionado
  } else {
    tarjeta.style.display = 'block'; // Mostrar el input cuando el checkbox no está seleccionado
    label_tarjeta.style.display = 'none'; // Ocultar el input cuando el checkbox está seleccionado
  }
}


function enviarPedido() {
    let comentariosData = [];
    let carritos = document.querySelectorAll('[id^="comentario_"]');
    carritos.forEach(function (textarea) {
        let id = textarea.id.split("_")[1];
        let comentario = textarea.value;
        comentariosData.push({ id_carrito: id, comentario: comentario });
    });
    
    // Convertir el array de objetos a formato JSON
    const comentariosDataJSON = JSON.stringify(comentariosData);
    
    let monto_pago = document.getElementById('monto_pago').value;
    let id_tarjeta = document.getElementById('id_tarjeta').value;
    const efectivo = document.getElementById('efectivo').checked;
    let direccion = document.getElementById('direccion').value;
    
    let formData = new FormData();
    formData.append('accion', 'Agregar');
    
    formData.append('monto_pago', monto_pago);
    formData.append('id_tarjeta', id_tarjeta);
    formData.append('efectivo', efectivo);
    formData.append('direccion', direccion);
    formData.append('comentariosData', comentariosDataJSON);    
    
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
      window.location.href = '/Hamburguesas/vistas/pedido.jsp';
    })
    .catch(error => {
      // Aquí puedes manejar errores en la solicitud o en la respuesta del servidor
      console.error('Fetch error:', error);
    });
        
}
