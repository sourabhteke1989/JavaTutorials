# Java 8 Default Methods
  - syntax - **default** void functionName(parameters) { ... }
  - provides default implementation in interface
  
## Rules 
- If "ClassMath" is implementing two interfaces "MathInterface" & "AnotherMathInterface", And both having same signature, then class "ClassMath" should provide implementation as multiple inheritance rule is broken. it will give compilation exception as "Duplicate default methods".


- Consuming interface default methods using InterfaceName.super.methodName() syntax (Only for default methods)
     - Class calling immediate implemented interface - Yes allowed
     - Class calling default method of not immediate implemented interface but in multi inheretance. - Not allowed.

```
	@Override
	public double divide(int a, int b) {
		return AnotherMathInterface.super.divide(a, b);
	}
```

- In case of multi level interface inheritance, all providing default implementation for same method, then when class calls this method last interface which class is implementing will be called.


- Consider below case, if both interfaces having implementation of same default method, there wont be any compilation error. When class calls method MathInterface method will get called.

```
			SuperInterface
					|
			MathInterface extends SuperInterface
					|
			ClassImplementation implements MathInterface, SuperInterface
```

