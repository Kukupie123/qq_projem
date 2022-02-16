import "./login.css"

import styled from 'styled-components';
import Input from "../components/input";
import {Button} from "react-bootstrap";
//css
import "bootstrap/dist/css/bootstrap.css"

export default () => <MainContainer>
    <WelcomeText>PROGEM</WelcomeText>
    <InputContainer>
        <Input type = "email" placeholder = "EMAIL"/>
        <Input type = "password" placeholder = "PASSWORD"/>
        <ButtonContainer>
            <Button className="active shadow" variant = "primary" about = "Log in as a user" type = "submit" active = {false}
            >Sign in</Button>
        </ButtonContainer>
    </InputContainer>
</MainContainer>

const MainContainer = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  height: 80vh;
  width: 30vw;
  background: rgba(255, 255, 255, 0.15);
  box-shadow: 0px 8px 32px rgba(50, 67, 151, 0.32);
  backdrop-filter: blur(8.5px);
  border-radius: 10px;
`;


const ButtonContainer = styled.div`
  width: 100%;

  height: 20%;
  margin: 1rem 0 20%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: end;
`;

const WelcomeText = styled.h2`
  margin: 3rem 0 2rem 0;
`;

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-contents: space-around;
  align-items: center;
  height: 100%;
  width: 100%;
`;


