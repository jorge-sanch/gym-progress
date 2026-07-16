async function cargarDiet() {
    try {
        const response = await fetch('/../components/diet.html');
        const html = await response.text();
        document.getElementById('diet-container').innerHTML = html;
    } catch (error) {
        console.error('Error cargando diet:', error);
    }
}