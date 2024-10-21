const express = require("express");
const cors = require('cors');
const bodyParser = require('body-parser');
const mysql = require( 'mysql2/promise');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

const myPool = mysql.createPool({
    host: '127.0.0.1',
    user: 'root',
    password: '',
    database: 'examen',
    connectionLimit: 10
});

myPool.getConnection((err, connection) => {
    if (err) {
        console.error('Error al conectar con la base de datos:', err);
        return;
    }
    console.log('Conexión exitosa a la base de datos');
    connection.release();
});

app.listen(PORT, () => {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
})

async function loginUser(email, password) {
    const [rows] = await myPool.query('SELECT * FROM usuarios WHERE email = ? AND password = ?', [email, password]);
    return rows;
}

app.post('/login', async (req, res) => {
    const { email, password } = req.body;

    try {
        const users = await loginUser(email, password);
        if (users && users.length > 0) {
            const user = users[0];
            res.json({ message: 'Login exitoso', user });
        } else {
            res.status(401).json({message: 'Invalid email or password'});
        }
    } catch (error) {
        console.error('Error al iniciar sesión: ', error);
        res.status(500).json({message: 'Error al iniciar sesión'});
    }
})