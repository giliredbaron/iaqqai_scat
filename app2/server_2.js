const WebSocket = require('ws');

const server = new WebSocket.Server({ port: 3001 });

server.on('connection', (ws) => {
    console.log('Client connected');
    
    ws.on('message', (message) => {
        console.log(`Received: ${message}`);
        ws.send(`Hello, you sent -> ${message}`);
    });

    ws.on('close', () => {
        console.log('Client disconnected');
    });

    ws.send('Welcome to the WebSocket server!');
});

console.log('WebSocket GGGG server running on ws://localhost:3001');
