async function cargarExercises() {
    try {
        const response = await fetch('/../components/exercises.html');
        const html = await response.text();
        document.getElementById('exercises-container').innerHTML = html;
    } catch (error) {
        console.error('Error cargando exercises:', error);
    }
}