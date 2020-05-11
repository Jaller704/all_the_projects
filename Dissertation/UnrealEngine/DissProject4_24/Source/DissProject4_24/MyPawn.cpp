// Fill out your copyright notice in the Description page of Project Settings.

#include "MyPawn.h"

#include "DissProject4_24.h"
#include "HeadMountedDisplay.h"
#include "MotionControllerComponent.h"
#include "Components/StaticMeshComponent.h"
#include "Engine/StaticMesh.h"

// Sets default values
AMyPawn::AMyPawn()
{
 	// Set this pawn to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = true;

	VROriginComp = CreateDefaultSubobject<USceneComponent>(TEXT("VRCameraOrigin"));
	VROriginComp->SetupAttachment(RootComponent);

	CameraComp = CreateDefaultSubobject<UCameraComponent>(TEXT("CameraComponent"));
	/* Assign to the VR origin component so any reset calls to the HMD can reset to 0,0,0 relative to this component */
	CameraComp->SetupAttachment(VROriginComp);


	MC_right = CreateDefaultSubobject<UMotionControllerComponent>(TEXT("RightHand"));
	MC_right->MotionSource = "Right";
	MC_right->SetupAttachment(VROriginComp);

	/*UStaticMeshComponent* SM_right = CreateDefaultSubobject<UStaticMeshComponent>(TEXT("RightHandMesh"));
	UStaticMesh* test = new UStaticMesh();
	SM_right->SetStaticMesh();
	SM_right->SetupAttachment(MC_right);*/


}

// Called when the game starts or when spawned
void AMyPawn::BeginPlay()
{
	Super::BeginPlay();
	
}

// Called every frame
void AMyPawn::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);

}

// Called to bind functionality to input
void AMyPawn::SetupPlayerInputComponent(UInputComponent* PlayerInputComponent)
{
	Super::SetupPlayerInputComponent(PlayerInputComponent);

}

