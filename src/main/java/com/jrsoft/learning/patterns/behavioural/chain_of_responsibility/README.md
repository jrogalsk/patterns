### Chain of responsibility

* `ChainOfResponsibility` consists of multiple `RequestHandlers` connected one to another
* `Client` sends `Request` to `ChainOfResponsibility` and assumes that it will be properly handled
* `RequestHandlers` pass request from one to another until one of them processes the request
* Make sure that `Request` is handeled if it reaches end of the chain
