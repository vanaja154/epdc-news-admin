(function () {
    function toggleMenu() {
        const navbar = document.getElementById("navbar").querySelector("ul");
        navbar.classList.toggle("active");
    }

    // Expose the function globally
    window.toggleMenu = toggleMenu;
})();