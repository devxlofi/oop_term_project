# OOP TERM PROJECT
Making a Simple Drawing Application with Java for a Term Project
<br/>
<br/>
<br/>

### KOREAN
## <img src="https://em-content.zobj.net/source/microsoft-teams/363/thinking-face_1f914.png" alt="프로젝트 소개" style="width:1em; height:1em"/> 프로젝트 소개

객체지향프로그래밍 강의 텀 프로젝트의 진행 과정과 변경사항을 기록하기 위해 만든 Git 레포지토리입니다. 이 프로젝트는 `java.awt`와 `javax.swing` 등의 라이브러리를 활용하여 기초적인 자바 스킬을 향상시키기 위해 진행합니다. 초기 커밋은 Hong Min 교수님께서 제공해 주셨으며, 미션 1~4까지의 과제가 포함되어 있습니다. 이 과제들은 초반에 해결되었고, 더 나은 그림판 애플리케이션으로 발전시켜보며 자바에 대해 더 깊이 이해하고자 합니다.

<details>
<summary><img src="https://em-content.zobj.net/source/microsoft-teams/363/check-mark_2714-fe0f.png" alt="해결한 미션" style="width:1em; height:1em;"/><b>&nbsp;해결한 미션 설명</b></summary>

## <img src="https://em-content.zobj.net/source/microsoft-teams/363/check-mark_2714-fe0f.png" alt="해결한 미션" style="width:1em; height:1em"/> 해결한 미션

### 미션1
`[Mission1]`부분에 `ActionListener`를 넣어 코드가 정상적으로 작동하도록 하였습니다.
`ColorHandler`와 `MenuHandler` 클래스가 이벤트를 처리하기 위해서는 `ActionListener` 인터페이스를 구현해야 합니다. `ActionListener`는 `actionPerformed(ActionEvent event)` 메서드를 정의하여, 사용자 인터페이스 이벤트(예: 버튼 클릭)를 처리할 수 있게 합니다. 


### 미션2
`draw(Graphics g)` 메서드는 `Graphics` 객체를 사용하여 사각형을 그리는 역할을 합니다. 좌표 및 크기를 계산하여 사각형을 그릴 수 있도록 구현해야 합니다.

```java
@Override
public void draw(Graphics g) {
    g.setColor(getColor());
    int x = Math.min(getStartX(), getEndX());
    int y = Math.min(getStartY(), getEndY());
    int width = Math.abs(getStartX() - getEndX());
    int height = Math.abs(getStartY() - getEndY());

    if (getFill()) {
        g.fillRect(x, y, width, height);
    } else {
        g.drawRect(x, y, width, height);
    }
}
```

- `draw` 메서드는 `Graphics` 객체를 사용하여 사각형을 그립니다.
- `getColor()` 메서드를 호출하여 도형의 색상을 설정합니다.
- `Math.min` 메서드를 사용하여 시작 좌표와 끝 좌표 중 작은 값을 선택하여 `x`와 `y` 좌표를 설정합니다.
- `Math.abs` 메서드를 사용하여 시작 좌표와 끝 좌표의 차이로 너비와 높이를 계산합니다.
- `getFill()` 메서드가 `true`일 경우, `fillRect` 메서드를 사용하여 사각형을 채워서 그립니다.
- `getFill()` 메서드가 `false`일 경우, `drawRect` 메서드를 사용하여 사각형의 테두리만 그립니다.


### 미션3
`[Mission 3]` 부분에 아래와 같은 코드를 추가하여 `mousePressed` 메서드를 완성했습니다.

```java
case "Oval":
    currentShapeObject = new GcuOval(event.getX(), event.getY(), 
                                     event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
    break;
```

- `mousePressed` 메서드는 마우스 클릭 이벤트를 처리합니다.
- `Oval` 선택 시, `GcuOval` 객체를 생성하여 `currentShapeObject`에 할당합니다.
- `GcuOval` 생성자는 시작 좌표 (`event.getX()`, `event.getY()`), 현재 색상 (`currentShapeColor`), 및 채우기 여부 (`currentShapeFilled`)를 사용하여 초기화합니다.
  


### 미션4

```java
@Override
public void mouseReleased(MouseEvent event) {
    currentShapeObject.setEndX(event.getX());
    currentShapeObject.setEndY(event.getY());
    myShapes.addFront(currentShapeObject);
    currentShapeObject = null;
    clearedShapes.makeEmpty();
    repaint();
}

@Override
public void mouseDragged(MouseEvent event) {
    currentShapeObject.setEndX(event.getX());
    currentShapeObject.setEndY(event.getY());
    repaint();
}
```

- **mouseReleased**: 마우스 버튼을 놓았을 때 호출됩니다.
  - 현재 도형의 끝 좌표를 업데이트합니다.
  - 도형을 `myShapes` 리스트에 추가합니다.
  - `currentShapeObject`를 null로 설정하고, `clearedShapes`를 초기화한 후, 화면을 다시 그립니다.

- **mouseDragged**: 마우스를 드래그할 때 호출됩니다.
  - 현재 도형의 끝 좌표를 업데이트합니다.
  - 화면을 다시 그립니다.

이 두 메서드는 마우스 이벤트를 처리하여 도형을 그리기 위해 사용됩니다.
</details>


## <img src="https://em-content.zobj.net/source/microsoft-teams/363/hammer-and-wrench_1f6e0-fe0f.png" alt="기능 추가 및 개선" style="width:1em; height:1em"/> 기능 추가 및 개선
