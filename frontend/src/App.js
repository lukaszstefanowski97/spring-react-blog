import React from 'react';
import logo from './logo.svg';
import spring from './spring.svg'
import './App.css';
import axios from 'axios';

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {};
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

    async componentDidMount() {
        let posts = await axios.get('http://localhost:8080/api/getPosts', {});
        this.setState(posts.data);

    }
}

export default App;
