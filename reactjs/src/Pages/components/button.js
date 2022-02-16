import styled from "styled-components";


export default function Button({content}) {
    return <StyledButton>
        {content}
    </StyledButton>
}


const StyledButton = styled.button`
  background: linear-gradient(to right, #14163c 0%, #03217b 79%);
  
`;