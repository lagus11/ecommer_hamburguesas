
function enviarFormulario() {
    
    let formulario = document.getElementById('formulario_producto');
    // Obtener los valores del formulario
    let nombre_producto = document.getElementById('nombre_producto').value;
    let id_categoria = document.getElementById('id_categoria').value;
    let descripcion_producto = document.getElementById('descripcion_producto').value;
    let precio = document.getElementById('precio').value;
    let estado = document.getElementById('estado').value;
    let imgUrl = document.getElementById('imgUrl').files[0]; // Aquí obtenemos la primera imagen seleccionada

    // Crear un objeto FormData para enviar los datos del formulario, incluida la imagen
    let formData = new FormData();
    formData.append('accion', 'Agregar');
    
    formData.append('nombre_producto', nombre_producto);
    formData.append('id_categoria', id_categoria);
    formData.append('descripcion_producto', descripcion_producto);
    formData.append('precio', precio);
    formData.append('estado', estado);
    formData.append('imgUrl', imgUrl);
   
    
   // Enviar los datos al controlador JSP usando una solicitud AJAX con FormData
    fetch('/Hamburguesas/ControladorProducto', {
      method: 'POST',
      body: formData
      
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      
        
    
    })
    .then(data => {
      formulario.reset();
      // Aquí puedes manejar la respuesta del servidor si es necesario
      // Recargar la página después de procesar la respuesta
      window.location.reload();
      
    })
    .catch(error => {
      // Aquí puedes manejar errores en la solicitud o en la respuesta del servidor
      console.error('Fetch error:', error);
    });

}
