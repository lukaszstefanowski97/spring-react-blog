import React from 'react';
import logo from './logo.svg';
import spring from './spring.svg'
import './App.css';
import axios from 'axios';
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import Button from "react-bootstrap/Button";

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
                <ButtonToolbar>
                    <Button variant="primary" size="lg" onClick={() => savePost(body)} active>
                        Add new post
                    </Button>
                </ButtonToolbar>
                </header>
            </div>
        )
    }

    async componentDidMount() {
        let posts = await axios.get('http://localhost:8080/api/getPosts', {});
        this.setState(posts.data);
    }
}

let body = {
    author: "test",
    content: "test"
};

function savePost(body) {
    axios.post('http://localhost:8080/api/savePost', {
        author: body.author,
        content: body.content
    })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
}

export default App;
