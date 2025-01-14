package org.fittrack.fitnesstrackerdemo.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Data
@Entity
@Table(name = "muscle_groups")
public class MuscleGroup {

    // will be a table editable only by admin account or simply added in database
    // since it will also have to be correlated with a workout type

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name identifier or the muscle group
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "name")
    private String name;

    //done RDB relationship
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "muscle_group_exercises",
            joinColumns = @JoinColumn(name = "muscle_group_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    private Set<Exercise> exercises;

    /*
     size of the muscle group will come in handy when calculating the total exhaustion points of a workout
     determining the level of the workout (from easy to hard)
     */
    @NotNull
    @Range(min = 5, max = 25)
    @Column(name = "size")
    private Integer size;


}
