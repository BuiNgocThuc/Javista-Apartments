FROM ubuntu:latest
LABEL authors="buing"

ENTRYPOINT ["top", "-b"]