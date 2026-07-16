async function cargarFooter() {
    try {
        const response = await fetch('/../components/footer.html');
        const html = await response.text();
        document.getElementById('footer-container').innerHTML = html;
    } catch (error) {
        console.error('Error cargando el footer:', error);
    }
}