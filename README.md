# Reinforcement Learning (RL)

Reference: First chapter of [Reinforcement Learning: An Introduction](https://www.andrew.cmu.edu/course/10-703/textbook/BartoSutton.pdf).

Two most important features of RL:
1. Trial-and-error search;
2. Delayed reward.

Challenge: Trade-off between exploration and exploitation.

Elements:
1. Agent;
2. Environment;
3. Policy;
    - It's the core of a RL agent.
    - It alone is sufficient to determine behavior.
4. Reward signal;
    - Defines the goal of a RL problem.
    - The agent's sole objective is to maximize the total reward it receives over the long run.
    - The reward signal is the primary basis for altering the policy.
5. Value function;
    - Specifies what is good in the long run.
    - The value of a state is the total amount of reward an agent can expect to accumulate over the future, starting from that state.
    - Rewards are given directly by the environment, but values must be estimated and re-estimted from the sequences of observations an agent makes over its entire lifetime.
6. Model of the environment (optional).
    - Models are used for planning.
    - Methods for solving RL problems that use models and planning are called model-based methods, as opposed to simpler model-free methods that are explicitly trial-and-error learners.
