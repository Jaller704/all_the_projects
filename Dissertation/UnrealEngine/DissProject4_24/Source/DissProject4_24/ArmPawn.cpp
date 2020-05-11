// Fill out your copyright notice in the Description page of Project Settings.


#include "ArmPawn.h"
#include "testCharacter.h"

// Sets default values
AArmPawn::AArmPawn()
{
 	// Set this pawn to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = true;

}

// Called when the game starts or when spawned
void AArmPawn::BeginPlay()
{
	Super::BeginPlay();
	
}

void AArmPawn::setPosition()
{
	//ACharacter* cont = Cast<ACharacter>(testCharacter);
}

// Called every frame
void AArmPawn::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);
	setPosition();
}

// Called to bind functionality to input
void AArmPawn::SetupPlayerInputComponent(UInputComponent* PlayerInputComponent)
{
	Super::SetupPlayerInputComponent(PlayerInputComponent);

}

