import React, { Component } from 'react';
import {
  Container,
  Col,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
} from 'reactstrap';
import './App.css';

const INITIAL_STATE = {
  username: '',
  password: '',
  message: '',
  authorized: false, 
};

class App extends Component {
  constructor(props) {
    super(props);

    this.state = { ...INITIAL_STATE };
  }
  onSubmit = async (event) => {
    event.preventDefault();

    const { username, password } = this.state;

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username: username, password: password }),
    };
    fetch('api/auth', requestOptions)
      .then((response) => response.json())
      .then((data) =>
        this.setState({ message: data.message, authorized: data.authorized })
      );

    console.log(username);
    console.log(password);
   

    // if authorized == true then redirect
  };

  onChange = (event) => {
    this.setState({ [event.target.name]: event.target.value, error: null });
  };

  render() {
    // const { username, password, error } = this.state;
    console.log(this.state.message);
    console.log(this.state.authorized);
    return (
      <Container className='App'>
        <h2>Log in</h2>
        <Form className='form' onSubmit={this.onSubmit}>
          <Col>
            <FormGroup>
              <Label>Username</Label>
              <Input
                type='email'
                name='username'
                id='exampleEmail'
                placeholder='username@email.com'
                // value={username}
                onChange={this.onChange}
              />
            </FormGroup>
          </Col>
          <Col>
            <FormGroup>
              <Label for='examplePassword'>Password</Label>
              <Input
                type='password'
                name='password'
                id='examplePassword'
                placeholder='********'
                // value={password}
                onChange={this.onChange}
              />
            </FormGroup>
          </Col>
          <Button>Submit</Button>
        </Form>
      </Container>
    );
  }
}

export default App;
