package nvt.doan.service;

import nvt.doan.entities.Room;
import nvt.doan.repository.HomestayRepository;
import nvt.doan.repository.RoomRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

@ContextConfiguration(classes = {RoomServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoomServiceImplTest {
    @MockBean
    private HomestayRepository homestayRepository;

    @MockBean
    private HomestayRepository homestayRepository1;

    @MockBean
    private ImageService imageService;

    @MockBean
    private JpaRepository jpaRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    /**
     * Method under test: {@link RoomServiceImpl#getRoomByHomestayId(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRoomByHomestayId() {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: Duplicate mock definition [MockDefinition@52f81592 name = '', typeToMock = nvt.doan.repository.HomestayRepository, extraInterfaces = set[[empty]], answer = RETURNS_DEFAULTS, serializable = false, reset = AFTER]
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        //       at java.util.Spliterator.forEachRemaining(Spliterator.java:326)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:958)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:127)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:502)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:488)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:543)
        //   See https://diff.blue/R026 to resolve this issue.

//       List<Room> rooms = roomServiceImpl.getRoomByHomestayId(1);
        return;
    }

    /**
     * Method under test: {@link RoomServiceImpl#getRoomRate(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRoomRate() {
        // TODO: Complete this test.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: Duplicate mock definition [MockDefinition@6227b62f name = '', typeToMock = nvt.doan.repository.HomestayRepository, extraInterfaces = set[[empty]], answer = RETURNS_DEFAULTS, serializable = false, reset = AFTER]
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        //       at java.util.Spliterator.forEachRemaining(Spliterator.java:326)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:958)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:127)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:502)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:488)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:543)
        //   See https://diff.blue/R026 to resolve this issue.

        assertSame(3, roomServiceImpl.getRoomRate(3));
    }
}

