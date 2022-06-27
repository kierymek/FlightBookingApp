import React from "react";

const TextInput = props => (
  <form onSubmit={props.onSubmit}>
      <label>{props.display}: </label>
      <input type="text"
             onChange={props.onChange}
             placeholder={`Enter your ${props.display}`} required/>
      <button className="btn btn-primary" type="submit">Submit</button>
  </form>
);

export default TextInput;