resource "aws_instance" "web" {
  ami = "ami-0f918f7e67a3323f0"
  instance_type = "t2.micro"
  vpc_security_group_ids = [aws_security_group.my-sg.id]
  tags = {
    Name = "web-instance"
  }
}

resource "aws_security_group" "my-sg" {
    name        = "my-sg"
    description = "My Security Group"

    ingress {
        from_port   = 80
        to_port     = 80
        protocol    = "tcp"
  
        cidr_blocks = ["0.0.0.0/0"]
    }
    egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]  
    }


}