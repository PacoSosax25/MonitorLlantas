/* dashboard.js — Filtro AJAX por tipo de unidad */

(function () {
    'use strict';

    const selectFiltro  = document.getElementById('filtroTipo');
    const cuerpo        = document.getElementById('cuerpoDashboard');

    if (!selectFiltro || !cuerpo) return;

    selectFiltro.addEventListener('change', function () {
        const tipo = this.value;
        const url  = tipo === 'TODOS'
            ? '/dashboard/llantas'
            : '/dashboard/llantas?tipo=' + encodeURIComponent(tipo);

        fetch(url, { headers: { 'Accept': 'application/json' } })
            .then(function (res) {
                if (!res.ok) throw new Error('Error al obtener datos: ' + res.status);
                return res.json();
            })
            .then(function (llantas) {
                renderizarFilas(llantas);
            })
            .catch(function (err) {
                console.error(err);
            });
    });

    function renderizarFilas(llantas) {
        cuerpo.innerHTML = '';

        if (!llantas || llantas.length === 0) {
            cuerpo.innerHTML =
                '<tr><td colspan="8" class="sin-datos">No hay datos para el tipo seleccionado</td></tr>';
            return;
        }

        llantas.forEach(function (llanta) {
            var fila = document.createElement('tr');
            fila.innerHTML =
                '<td class="text-left"><strong>' + esc(llanta.unidad) + '</strong></td>' +
                '<td>' + esc(llanta.tipo) + '</td>' +
                crearCelda(llanta.delanteroIzquierdo) +
                crearCelda(llanta.delanteroDerecho) +
                crearCelda(llanta.traseraIzquierdaInterior) +
                crearCelda(llanta.traseraIzquierdaExterior) +
                crearCelda(llanta.traseraDerechaInterior) +
                crearCelda(llanta.traseraDerechaExterior);
            cuerpo.appendChild(fila);
        });
    }

    function crearCelda(celda) {
        var cssClass = celda && celda.color ? 'celda-' + celda.color : '';
        var texto    = celda ? esc(celda.valorFormateado) : '-';
        return '<td class="' + cssClass + '">' + texto + '</td>';
    }

    /* Escapa caracteres HTML para evitar XSS */
    function esc(valor) {
        if (valor === null || valor === undefined) return '-';
        return String(valor)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;');
    }

})();
