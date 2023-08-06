const menuBoton = document.getElementById('menu-boton');
const menu = document.getElementById('menu');

menuBoton.addEventListener('click', () => {
    
    if (menu.style.left == '0%'){
        menu.style.left = '100%';
    }
    else {
        menu.style.left = '0%';
    }    

});