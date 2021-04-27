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
  authorized: false
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
      .then((data) => {
        if (data.authorized === true) {
          console.log(data.message);
        } else {
          console.error(data.message);
        }

        this.setState({
          authorized: data.authorized
        });
      });
  };

  onChange = (event) => {
    this.setState({ [event.target.name]: event.target.value, error: null });
  };

  render() {
    const { authorized } = this.state;

    return (
      authorized === false ?
        <Container className='App'>
          < h2 > Log in</h2 >
          <Form className='form' onSubmit={this.onSubmit}>
            <Col>
              <FormGroup>
                <Label>Username</Label>
                <Input
                  type='email'
                  name='username'
                  id='exampleEmail'
                  placeholder='username@email.com'
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
                  onChange={this.onChange}
                />
              </FormGroup>
            </Col>
            <Button>Submit</Button>
          </Form>
        </Container >
        : <Label>SQL injection attack successful!</Label>
    );
  }
}

export default App;
