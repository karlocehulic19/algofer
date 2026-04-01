package main

import (
	"context"

	"github.com/docker/go-sdk/container"
	"github.com/docker/go-sdk/image"
	"github.com/gin-gonic/gin"
)

func getHello(c *gin.Context) {
	c.String(200, "Hello, AlgoFER user!")
}

type CodeSubmit struct {
	Code string `json:"code" binding:"required"`
}

func postDemoSolution(c *gin.Context) {
	var codeSubmit CodeSubmit

	if err := c.ShouldBindJSON(&codeSubmit); err != nil {
		c.String(400, "Invalid JSON")
		return
	}

	tag := copy_and_compile()
	build_and_run_container(tag)
	c.JSON(200, gin.H{
		"status":     "PASS",
		"codeSubmit": codeSubmit,
	})
}

func copy_and_compile() string {
	ctx := context.Background()

	tag, err := image.BuildFromDir(ctx, "./compiler", "Dockerfile", "test-go")
	if err != nil {
		panic(err)
	}
	print("tag :", tag)
	return tag
}

func build_and_run_container(tag string) {
	ctx := context.Background()

	_, err := container.Run(ctx, container.WithImage(tag))
	if err != nil {
		panic(err)
	}
}

func main() {
	router := gin.Default()
	router.GET("/hello", getHello)
	router.POST("/api/v1/oop/demo/submit", postDemoSolution)

	router.Run("0.0.0.0:8000")
}
