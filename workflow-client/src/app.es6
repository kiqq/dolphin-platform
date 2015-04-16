"use strict";
try {
    require('babelify/polyfill');
} catch (e) {
    // This will throw an error if polyfill is
    // already required.
}

import * as dolphin from '../../javascript-client/dist/dolphin.min.js';

const SERVER_URL = 'http://localhost:8080/dolphin';

dolphin.connect(SERVER_URL)

    .onAdded('ProcessList',
        (data) => {
            document.querySelector('workflow-main-view').data = data;

            //window.setInterval(() => {console.log(data)}, 1000);
        }
    )

    .send('ProcessDefinitionCommands:fetchAll');
