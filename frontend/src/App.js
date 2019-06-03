import React from 'react';
import logo from './logo.svg';
import spring from './spring.svg'
import './App.css';
import axios from 'axios';

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = [];
    }

    render() {
        return (
            <div className="wrap">
                <header className="App-header">
                    <header>
                        <img src={spring} className="App-logo-spring" alt="spring"/>
                        <img src={logo} className="App-logo" alt="logo"/>
                        <h1 className="string">Spring + React blog</h1>
                    </header>
                </header>
            </div>
        )
    }
}

function getPosts() {
    axios.get('http://localhost:8080/api/getPosts', {})
        .then(function (response) {
            console.log(response);
            console.log('Getting list of posts...');
        })
        .catch(function (error) {
            console.log(error);
        });
}

export default App;
