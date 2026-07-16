async function cargarDiet() {
    try {
        const response = await fetch('/../components/weight.html');
        const html = await response.text();
        document.getElementById('weight-container').innerHTML = html;
    } catch (error) {
        console.error('Error cargando weight:', error);
    }
}