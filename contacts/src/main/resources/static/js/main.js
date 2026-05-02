document.addEventListener('DOMContentLoaded', () => {
    const selector = document.getElementById('locales');

    selector.addEventListener('change', () => {
        const selectedOption = selector.value;
        const url = new URL(window.location.href);
        if (selectedOption !== '') {
            url.searchParams.set('lang', selectedOption);
            window.location.href = url.pathname + url.search;
        }
    });
});