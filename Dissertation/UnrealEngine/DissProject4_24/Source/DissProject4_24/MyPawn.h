// Fill out your copyright notice in the Description page of Project Settings.

#pragma once

#include "CoreMinimal.h"
#include "GameFramework/Pawn.h"
#include "MyPawn.generated.h"
#include "Camera/CameraComponent.h"

UCLASS()
class DISSPROJECT4_24_API AMyPawn : public APawn
{
	GENERATED_BODY()

public:
	// Sets default values for this pawn's properties
	AMyPawn();

protected:
	// Called when the game starts or when spawned
	virtual void BeginPlay() override;

	UPROPERTY(VisibleAnywhere, Category = "Components")
		UCameraComponent* CameraComp;

	UPROPERTY(VisibleAnywhere, Category = "Components")
		USceneComponent* VROriginComp;

	UPROPERTY(EditDefaultsOnly, Category = "VR")
		bool bPositionalHeadTracking;

	UPROPERTY(EditDefaultsOnly, Category = "Components")
		class UMotionControllerComponent* MC_right;

	/*UPROPERTY(EditDefaultsOnly, Category = "Components")
		class UStaticMeshComponent* SM_right;*/

public:	
	// Called every frame
	virtual void Tick(float DeltaTime) override;

	// Called to bind functionality to input
	virtual void SetupPlayerInputComponent(class UInputComponent* PlayerInputComponent) override;

};
