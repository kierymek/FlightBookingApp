import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";

function Navigator() {
  return (
    // <Navbar bg="dark" expand="lg" variant="dark">
    //   <Container>
    //     <Navbar.Brand href="#home">Flight Navigator</Navbar.Brand>
    //     <Navbar.Toggle aria-controls="basic-navbar-nav" />
    //     <Navbar.Collapse id="basic-navbar-nav">
    //       <Nav className="me-auto">
    //         <Nav.Link href="/">Login</Nav.Link>
    //         <Nav.Link href="/search">Search</Nav.Link>
    //         <NavDropdown title="Dropdown" id="basic-nav-dropdown">
    //           <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
    //           <NavDropdown.Item href="#action/3.2">
    //             Another action
    //           </NavDropdown.Item>
    //           <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
    //           <NavDropdown.Divider />
    //           <NavDropdown.Item href="#action/3.4">
    //             Separated link
    //           </NavDropdown.Item>
    //         </NavDropdown>
    //       </Nav>
    //     </Navbar.Collapse>
    //   </Container>
    // </Navbar>
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/">Flight Navigator</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/">Login</Nav.Link>
          <Nav.Link href="/search">Search</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
}

export default Navigator;
