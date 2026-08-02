async function cargarHeader() {
    try {
        const response = await fetch('/../components/header.html');
        const html = await response.text();
        document.getElementById('header-container').innerHTML = html;
    } catch (error) {
        console.error('Error cargando el header:', error);
    }
}